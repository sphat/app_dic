package fr.paris.lutece.plugins.rokmeul.business.dao;

import javax.servlet.http.HttpServletRequest;

import org.junit.Test;
import org.mockito.Mockito;

import fr.paris.lutece.plugins.rokmeul.business.dal.Post;
import fr.paris.lutece.test.LuteceTestCase;

public class PostHomeTest extends LuteceTestCase {

    @Test
    public void testCreate() throws Exception {
        // Given
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);

        Post iPost = new Post();
        iPost.setTitle("EBC Trek Day 14 Monjo to Lukla");
        iPost.setText(
                "I pushed it from Dole to Monjo. It's a 6h50 walking (16 Km) and I arrived at Monjo yesterday before the sunset. There is a handful of the lodge in Monjo. Despite in the high tourist season, I can easily find a place to stay up on my arrival and without booking in advance.");
        iPost.setTextHtml(
                "The lodge I stay located along the little river stream between the valley and face to the trekker road. So from time to time, when the porter passes through the village, the sound of the yak bells mix with the river flow and the singing birds makes a beautiful atmosphere.");
        iPost.setHeadText(
                "The lodge veranda face to the mountain gives a green view to the village’s vegetable garden, two little tables, and some chairs installed on the veranda and I just sit there take a break to enjoy some hot black tea, and seeing the sunset.");
        iPost.setHeadTextHtml("Emm! I talk to myself: don't you think this is the paradise?");
        iPost.setTextDisplayOnIndex("Fill your life with adventures, not things.\r\n"
                + "Have stories to tell, not stuff to show.\r\n" + "-author unknown");
        iPost.setDisplayOnIndex(false);
        iPost.setCreationUser("sphat");
        iPost.setImageDisplayOnIndex("23d884f1acecf4aa8f5a3bc7510b1179.jpg");

        // When
        PostHome.create(iPost, request);

        // Then
    }

}
