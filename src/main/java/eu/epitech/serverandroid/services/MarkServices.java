package eu.epitech.serverandroid.services;

import eu.epitech.serverandroid.beans.Mark;
import eu.epitech.serverandroid.beans.Params;
import eu.epitech.serverandroid.dao.MarkDao;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MarkServices {

    @RequestMapping(value = "/mark", method = RequestMethod.POST)
    public String getAllMark(@ModelAttribute Params<Mark> params) {
        MarkDao dao = new MarkDao();
        return (dao.getAllMark(params));
    }

    @RequestMapping(value = "/mark/add", method = RequestMethod.POST)
    public String addMark(@ModelAttribute Params<Mark> params) {
        MarkDao dao = new MarkDao();
        return (dao.addMark(params));
    }
}
