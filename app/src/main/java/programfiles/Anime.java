package programfiles;


public class Anime {
        private String score ;
        private String title ;
        private String image_url ;
        private int mal_id ;
    public  Anime (String title  , String score , Integer mal_id , String image_url ){
        this.title = title ;
        this.score = score ;
        this.mal_id =mal_id;
        this.image_url=image_url;
    }


        public String getTitle (){
        return title ;
    }
    public String getScore () {
        return score ;
    }
    public int getMal_id (){
        return mal_id ;
    }
    public String getImage_url(){
        return image_url ;
        }
    }
