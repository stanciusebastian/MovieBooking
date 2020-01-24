package com.example.moviebookingws.recommender;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.model.JDBCDataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.apache.mahout.cf.taste.impl.model.jdbc.MySQLJDBCDataModel;
import org.apache.mahout.common.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.EntityManagerFactoryInfo;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.List;

@Component
public class CollaborativeFiltering {

    @Autowired
    EntityManager entityManager;
    private final String preferenceTable="user_movie";


    private DataSource getDataSourceFromMySqlEntityManager() {
        EntityManagerFactoryInfo info = (EntityManagerFactoryInfo) entityManager.getEntityManagerFactory();
        return info.getDataSource();
    }

    private UserSimilarity getCorellationCoefficient(DataModel dataModel) {
        try {
            return new PearsonCorrelationSimilarity(dataModel);
        } catch (TasteException e) {
            e.printStackTrace();
        }
        return null;
    }

    private UserNeighborhood getUserNeighborhood(Integer n, UserSimilarity userSimilarity, DataModel dataModel) {
        try {
            return new NearestNUserNeighborhood(n,userSimilarity,dataModel);
        } catch (TasteException e) {
            e.printStackTrace();
        }
        return null;
    }

    private UserBasedRecommender getUserBasedRecommender(DataModel dataModel, UserNeighborhood userNeighborhood, UserSimilarity userSimilarity) {
        return new GenericUserBasedRecommender(dataModel,userNeighborhood,userSimilarity);
    }

    public List<RecommendedItem> getRecommendations(long userID, int noOfRecommendations) throws TasteException {
        JDBCDataModel dataModel = new MySQLJDBCDataModel(getDataSourceFromMySqlEntityManager(),preferenceTable,"user_id","movie_id","rating","");
        RandomUtils.useTestSeed();
        UserSimilarity userSimilarity=getCorellationCoefficient(dataModel);
        UserNeighborhood userNeighborhood=getUserNeighborhood(10,userSimilarity,dataModel);
        UserBasedRecommender recommender=getUserBasedRecommender(dataModel,userNeighborhood,userSimilarity);
        return recommender.recommend(userID,noOfRecommendations);
    }
}