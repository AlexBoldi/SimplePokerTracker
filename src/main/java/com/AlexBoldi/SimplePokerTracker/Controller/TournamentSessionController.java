package com.AlexBoldi.SimplePokerTracker.Controller;

import com.AlexBoldi.SimplePokerTracker.Domain.TournamentSession;
import com.AlexBoldi.SimplePokerTracker.Service.TournamentSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/tournaments")
public class TournamentSessionController {

    @Autowired
    private TournamentSessionService tournamentSessionService;

    @RequestMapping(method = RequestMethod.GET)
    public String listTournamentSessions(Model model) {
        List<TournamentSession> tournamentSessions = tournamentSessionService.getAll();
        model.addAttribute("tournamentSessions", tournamentSessions);
        model.addAttribute("tournamentSession", new TournamentSession());
        return "listTournamentSessions";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public String deleteById(@PathVariable int id) {
        tournamentSessionService.deleteById(id);
        return "redirect:/tournaments";
    }

}
