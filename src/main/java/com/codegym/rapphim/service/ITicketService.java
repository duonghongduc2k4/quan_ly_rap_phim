package com.codegym.rapphim.service;

import com.codegym.rapphim.model.Ticket;

public interface ITicketService extends IGenerateService<Ticket>{
Ticket saveTicket (Ticket ticket);
}
