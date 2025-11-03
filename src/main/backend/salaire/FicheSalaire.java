package main.backend.salaire;

import java.util.Map;

public class FicheSalaire {
    private Map<String, Double> rubriques;
    private double gain_imposable_fixe;
    private double salaire_net;

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

}
