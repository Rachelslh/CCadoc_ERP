package POJO;

import DAO.*;
import Exceptions.BuildingException;
import Exceptions.NonExistantDansLaBDD;
import Interfaces.DAOAble;
import Interfaces.Descriptible;

import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;

public class SiteWeb implements DAOAble<SiteWeb>, Descriptible<SiteWeb> {
    private String url, description;

    public SiteWeb() {}

    public SiteWeb(String url, String description) {
        this.url = url;
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object obj) {
        SiteWeb s=(SiteWeb)obj;
        return this.url.equals(s.url);
    }

    @Override
    public HashMap<String, String> getRepositoryAttributs() throws IllegalAccessException {
        HashMap<String,String> H = new HashMap<>();
        H.put("URL",url);
        H.put("DESCRIPTION_URL",description);
        return H;
    }

    @Override
    public String getTableName() {
        return "SITEW";
    }

    @Override
    public String getReference() {
        return url;
    }

    @Override
    public String getKeyName() {
        return "URL";
    }

    public String getName() {
        return "DESCRIPTION_URL";
    }

    @Override
    public SiteWeb buildFromRepData(HashMap<String, String> H) throws BuildingException, IllegalAccessException {
        SiteWeb SW = new SiteWeb();
        if(H.containsKey("DESCRIPTION_URL"))
            SW.description = H.get("DESCRIPTION_URL");
        if(H.containsKey("URL"))
            SW.url = H.get("URL");
        return SW;
    }

    @Override
    public void Maj_BDD(String attribut, String nouvelle_valeur, String ref) throws SQLException, IllegalAccessException, NonExistantDansLaBDD, BuildingException {
        DAO<SiteWeb> DSW = new DAOSiteWeb(new SiteWeb());
        SiteWeb SW= DSW.recuperer(ref);
        switch (attribut){
                case "DESCRIPTION_URL":SW.description=nouvelle_valeur;
                    break;
                case "URL":SW.url=nouvelle_valeur;
                    break;
            }
        DSW.mettre_a_jour(SW,ref);
    }

    @Override
    public boolean verfier(String s) {
        return false;
    }

    @Override
    public Modele_Contact<SiteWeb> getModeleContact() throws IllegalAccessException {
        return new Modele_Contact<SiteWeb>(this);
    }
}

