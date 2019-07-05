/**
    Classe que conté les estadistiques de cada element de l'array
    @author	Alex Almansa, Marc Llort
 *//*
    @param id:
    @param statistics: classe que conte la informacio detallada del canal o video
    @param snippet: classe que conte la informacio de la playlist, elements que te una playlist o una llista de videos
        */

public class Estadistiques extends ArrayVideo {

    private String id;
    private statistics statistics = new statistics();
    private tipusSnippet snippet = new tipusSnippet();

    /** @return id*/
    public String getId() {
        return id;
    }

    /** @return estadistiques*/
    public statistics getStatistics() {
        return statistics;
    }

    /** @param  id :identificador de l'element*/
    public void setId(String id) {
        this.id = id;
    }

    /** @return snippet*/
    public tipusSnippet getSnippet() {
        return snippet;
    }
}
