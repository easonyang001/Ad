package com.simpleAdServer.service;


import com.simpleAdServer.DTO.ServerCampaighRequest;
import com.simpleAdServer.DTO.ServerCampaigh;
import com.simpleAdServer.error.ServerCampaighException;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ServerCampaighRepo {

    private static ServerCampaighRepo instance = new ServerCampaighRepo();

    private Map<String, ServerCampaigh> repo = null;

    private ServerCampaighRepo(){
        repo = new ConcurrentHashMap<String, ServerCampaigh>();
    }

    public static ServerCampaighRepo getInstance() {
        return instance;
    }

    public Map<String, ServerCampaigh> getRepo() {
        return repo;
    }

    public void add(ServerCampaighRequest request) {
        if(existInRepo(request.getPartnerId())){
            throw new ServerCampaighException();
        }
        ServerCampaigh ad = new ServerCampaigh();
        ad.setAdContent(request.getAdContent());
        ad.setPartnerId(request.getPartnerId());
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE,request.getDuration());
        ad.setExpiration(cal.getTime());
        repo.put(request.getPartnerId(),ad);
    }

    private boolean existInRepo(String partnerId) {
        boolean res = false;
        if (repo.containsKey(partnerId)) {
        	ServerCampaigh ad = repo.get(partnerId);
            if(new Date().after(ad.getExpiration())){//if expired
                res = true;
            }
        }
        return res;
    }
}
