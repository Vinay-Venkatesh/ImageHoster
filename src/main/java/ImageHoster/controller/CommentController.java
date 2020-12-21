package ImageHoster.controller;

import ImageHoster.model.Comment;
import ImageHoster.model.Image;
import ImageHoster.model.User;
import ImageHoster.service.CommentService;
import ImageHoster.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;

@Controller
public class CommentController {

    @Autowired
    CommentService commentService;

    @Autowired
    ImageService imageService;

    @RequestMapping(value="/image/{imageId}/{imageTitle}/comments", method = RequestMethod.POST)
    public String createComments(@PathVariable("imageId") int imageId, @PathVariable("imageTitle") String imageTitle, @RequestParam("comment") String comment_text, HttpSession session) {
        User user = (User) session.getAttribute("loggeduser");

        //Get the image from the imageId taken from path variable.
        Image image = imageService.getImage(imageId);

        //Creating new comment object to set comment,image,date and user for the comment.
        Comment comment = new Comment();
        comment.setText(comment_text);
        comment.setImage(image);
        comment.setDate(LocalDate.now());
        comment.setUser(user);
        commentService.createComments(comment);
        return "redirect:/images/" + image.getId() + '/' + image.getTitle();
    }

}