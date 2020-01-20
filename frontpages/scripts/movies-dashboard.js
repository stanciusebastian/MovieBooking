 var headers = {'Authorization': "Bearer " + "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtaWhhaUBlbWFpbC5jb20iLCJleHAiOjE1Nzk2MjM4NDR9.49subWGog9uJx2nOwqjUzUzmIo-NvTZOfON2pkYSYzR5TF6db9iivUiphT54XaiWKkbv8iaKQk1iYQ3jbvOpoA"}
function getMovies() {
    $.ajax({
        url: "http://localhost:8080/movies/f0cho12s24qflkk76kaa197197o0e2",
        headers: headers,
        success: function(result){
            console.log(result)
        },
        error: function(result) {
            console.log(result)
        }
    });
}

$(document).ready(function(){
    getMovies();
});