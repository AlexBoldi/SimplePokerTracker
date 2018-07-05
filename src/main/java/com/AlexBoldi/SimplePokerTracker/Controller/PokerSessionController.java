package com.AlexBoldi.SimplePokerTracker.Controller;

import com.AlexBoldi.SimplePokerTracker.Domain.PokerSession;
import com.AlexBoldi.SimplePokerTracker.Service.PokerSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Controller
public class PokerSessionController {

    @Autowired
    private PokerSessionService pokerSessionService;

    @RequestMapping(name = "/sessions", method = RequestMethod.GET)
    public String listPokerSessions(Model model) {
        List<PokerSession> pokerSessions = pokerSessionService.getAll();
        model.addAttribute("pokerSessions", pokerSessions);
        model.addAttribute("pokerSession", new PokerSession());
        List<PokerSession> pokerStats = pokerSessionService.getStats();
        model.addAttribute("pokerStats", pokerStats);
        model.addAttribute("pokerSession", new PokerSession());
        List<PokerSession> resultsOverTime = pokerSessionService.getResultsOverTime();
        model.addAttribute("resultsOverTime", resultsOverTime);
        model.addAttribute("pokerSession", new PokerSession());
        pokerSessionService.accumulateResultsOverTime(resultsOverTime);
        Collections.reverse(resultsOverTime);
        pokerSessionService.writeCsvFile(resultsOverTime, "C:/Java Project/src/main/resources/Templates/resultsOverTime.csv");
        return "listPokerSessions";
    }

    @RequestMapping(value = "sessions/{pokerSessionId}", method = RequestMethod.POST)
    public String deleteSessionById(@PathVariable int pokerSessionId) {
        pokerSessionService.deletePokerSessionById(pokerSessionId);
        return "redirect:/sessions";
    }

}