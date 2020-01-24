    var headers = {
    'Authorization': "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtaWhhaUBlbWFpbC5jb20iLCJleHAiOjE1Nzk2MjM4NDR9.49subWGog9uJx2nOwqjUzUzmIo-NvTZOfON2pkYSYzR5TF6db9iivUiphT54XaiWKkbv8iaKQk1iYQ3jbvOpoA"
    'test':'test'}

   $(document).ready(function(){
       $.ajax({
               url: "http://localhost:8080/movies-schedule/2ic9b9anel3ftiqcphietohrb7rn8h",
               type: "GET",
               headers: headers,
               success: function(result){
                   console.log(result)
               },
               error: function(result) {
                   console.log(result)
               }
           });
   });