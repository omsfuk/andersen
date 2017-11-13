package cn.omsfuk.andersen.controller;

import cn.omsfuk.andersen.controller.support.GroupPost;
import cn.omsfuk.andersen.dao.PostDAO;
import cn.omsfuk.andersen.dao.TagDAO;
import cn.omsfuk.andersen.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Talk is cheap. Show me the code
 * 多说无益，代码上见真章
 * -------  by omsfuk  2017/8/19
 */

@Controller
public class BasicController {

    @Autowired
    private PostDAO postDAO;

    @Autowired
    private TagDAO tagDAO;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model, @RequestParam(defaultValue = "0") Integer page) {
        Page<Post> postPage = postDAO.findPostsOrderByDate(new PageRequest(page, 10));
        model.addAttribute("page", postPage);
        model.addAttribute("currentPage", page);
        return "index";
    }
    
    @RequestMapping(value = "archive", method = RequestMethod.GET)
    public String archive(@RequestParam(defaultValue = "0") Integer page, Model model) {
        Page<Post> postPage = postDAO.findPostsOrderByDate(new PageRequest(page, 10));
        List<Post> posts = postPage.getContent();

        List<GroupPost> postGroups = new LinkedList<>();
        if (posts != null && posts.size() != 0) {
            System.out.println(posts.get(0));
            Date date = posts.get(0).getDate();
            GroupPost postGroup = new GroupPost(date);
            postGroup.getPosts().add(posts.get(0));
            postGroups.add(postGroup);
            for (int i = 1; i < posts.size(); i++) {
                if (!(posts.get(i).getDate().getMonth() == date.getMonth() && posts.get(i).getDate().getYear() == date.getYear())) {
                    postGroup = new GroupPost(posts.get(i).getDate());
                    postGroups.add(postGroup);
                    date = posts.get(i).getDate();
                }
                postGroup.getPosts().add(posts.get(i));
            }
        }
        model.addAttribute("pageCount", postPage.getTotalPages());
        model.addAttribute("currentPage", page);
        model.addAttribute("postGroups", postGroups);
        return "archive";
    }
    
    @RequestMapping(value = "post", method = RequestMethod.GET)
    public String post() {
        return "post";
    }
    
    @RequestMapping(value = "/post/{uri}", method = RequestMethod.GET)
    public String post_uri(@PathVariable String uri, Model model) {
        Post post = postDAO.findPostByUri(uri);
        model.addAttribute("post", post);
        return "post";
    }

    @RequestMapping(value = "tags", method = RequestMethod.GET)
    public String tags(String name, @RequestParam(defaultValue = "0") Integer page, Model model) {
        Set<String> tags = tagDAO.getTags();
        for (String tag : tags) {
            System.out.println(tag);
        }
        model.addAttribute("tags", tags);
        if (name == null) {

        }
        Page<Post> postPage = postDAO.findPostsOrderByDate(new PageRequest(page, 10));
        model.addAttribute("page", postPage);
        model.addAttribute("pageCount", postPage.getTotalPages());
        model.addAttribute("currentPage", page);
        return "tags";
    }
    
    @RequestMapping(value = "books", method = RequestMethod.GET)
    public String books(Model model) {
        Post post = postDAO.findBooks();
        model.addAttribute("post", post);
        return "books";
    }
    
    @RequestMapping(value = "about", method = RequestMethod.GET)
    public String about(Model model) {
        Post post = postDAO.findAbout();
        model.addAttribute("post", post);
        return "post";
    }

    @RequestMapping(value = "links", method = RequestMethod.GET)
    public String links(Model model) {
        Post post = postDAO.findLinks();
        model.addAttribute("post", post);
        return "post";
    }

}
