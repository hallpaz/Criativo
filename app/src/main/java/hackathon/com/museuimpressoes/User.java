package hackathon.com.museuimpressoes;

/**
 * Created by hallpaz on 12/10/2015.
 */
public class User {

    String name;
    String comment;
    String age;
    int photoId;

    User(String name, String age, int photoId, String comment) {
        this.name = name;
        this.age = age;
        this.photoId = photoId;
        this.comment = comment;
    }
}
