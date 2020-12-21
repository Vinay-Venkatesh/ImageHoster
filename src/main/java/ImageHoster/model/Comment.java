package ImageHoster.model;

import java.time.LocalDate;
import javax.persistence.*;


//@Entity annotation specifies that the corresponding class is a JPA entity
@Entity
//@Table annotation provides more options to customize the mapping.
//Here the name of the table to be created in the database is explicitly mentioned as 'images'. Hence the table named 'images' will be created in the database with all the columns mapped to all the attributes in 'Image' class
@Table(name = "comment")
public class Comment {

    //@Id annotation specifies that the corresponding attribute is a primary key
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@Column annotation specifies that the attribute will be mapped to the column in the database.
    //Here the column name is explicitly mentioned as 'id'
    @Column(name = "id")
    private Integer id;

    @Column(columnDefinition = "TEXT")
    private String text;

    @Column(name = "date")
    private LocalDate date;

    // Many:One mapping between comment and users table
    // One user can post multiple comments , one comment should belong to only one user.
    @ManyToOne(fetch = FetchType.EAGER)
    // this is the name of the column in comment table which is
    // primary key in users table.
    @JoinColumn(name = "user_id")
    private User user;

    // Many:One mapping between comment and image table
    // One Image can have multiple comments , one comment should belong to only one image.
    @ManyToOne(fetch = FetchType.EAGER)
    // this is the name of the column in comment table which is
    // primary key in image table.
    @JoinColumn(name = "image_id")
    private Image image;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}

