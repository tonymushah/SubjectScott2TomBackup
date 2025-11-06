package main.backend.salaire;

import java.util.Map;
import java.util.Map.Entry;

public class FicheSalaire {
    private Map<String, Double> rubriques;
    private double gain_imposable_fixe;
    private double salaire_net;
    private String empnom;
    private String periode;

    public Map<String, Double> getRubriques() {
        return rubriques;
    }

    public void setRubriques(Map<String, Double> rubriques) {
        this.rubriques = rubriques;
    }

    public double getGain_imposable_fixe() {
        return gain_imposable_fixe;
    }

    public void setGain_imposable_fixe(double gain_imposable_fixe) {
        this.gain_imposable_fixe = gain_imposable_fixe;
    }

    public double getSalaire_net() {
        return salaire_net;
    }

    public void setSalaire_net(double salaire_net) {
        this.salaire_net = salaire_net;
    }

    public FicheSalaire() {
    }

    public FicheSalaire(Map<String, Double> rubriques, double gain_imposable_fixe, double salaire_net) {
        this.rubriques = rubriques;
        this.gain_imposable_fixe = gain_imposable_fixe;
        this.salaire_net = salaire_net;
    }

    public String getEmpnom() {
        return empnom;
    }

    public void setEmpnom(String empnom) {
        this.empnom = empnom;
    }

    public String getPeriode() {
        return periode;
    }

    public void setPeriode(String periode) {
        this.periode = periode;
    }

    public String htmlPrint() {
        StringBuilder builder = new StringBuilder();
        builder.append("<div class='fiche-salaire'>");
        builder.append(String.format("<h2>Salaire de %s</h2>", this.getEmpnom()));
        builder.append(String.format("<p>Periode: %s</p>", this.getPeriode()));
        builder.append("<section class='fs-rubriques'>");

        builder.append("<h3>Rubriques</h3>");
        for (Entry<String, Double> rubrique : this.getRubriques().entrySet()) {
            builder.append("<article class='rubrique'>");
            builder.append(String.format("<span class='rubrique-nom'>%s:</span> <span>%s</span>", rubrique.getKey(),
                    rubrique.getValue().toString()));
            builder.append("</article>");
        }

        builder.append("</section>");
        builder.append("<hr/>");
        builder.append("<section class='total'>");
        builder.append(String.format("<h3>Salaire net: %s</h3>", this.getSalaire_net()));
        builder.append("</section>");

        builder.append("</div>");
        return builder.toString();
    }

}
