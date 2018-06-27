package com.AlexBoldi.SimplePokerTracker.Controller;

import com.AlexBoldi.SimplePokerTracker.Domain.PokerSession;
import com.AlexBoldi.SimplePokerTracker.Service.PokerSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/sessions")
public class PokerSessionController {

    @Autowired
    private PokerSessionService pokerSessionService;

    @RequestMapping(method = RequestMethod.GET)
    public String listPokerSessions(Model model) {
        List<PokerSession> pokerSessions = pokerSessionService.getAll();
        model.addAttribute("pokerSessions", pokerSessions);
        model.addAttribute("pokerSession", new PokerSession());
        return "listPokerSessions";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String createPokerSession(PokerSession pokerSession, Model model) {
        pokerSessionService.createPokerSession(pokerSession);
        List<PokerSession> pokerSessions = pokerSessionService.getAll();
        model.addAttribute("pokerSessions", pokerSessions);
        return "listPokerSessions";
    }

    @RequestMapping(value = "/{pokerSessionId}", method = RequestMethod.POST)
    public String deleteSessionById(@PathVariable int pokerSessionId) {
        pokerSessionService.deletePokerSessionById(pokerSessionId);
        return "redirect:/sessions";
    }

}