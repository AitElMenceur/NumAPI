package com.example.projetandroid;

public enum Compagny {
    Accor("hotels", "AC.PA", "Accor"),
    Air_Liquide("commodity chemicals", "AI.PA", "Air Liquide"),
    Airbus("aerospace", "AIR.PA", "Airbus"),
    ArcelorMittal("steel", "MT.AS", "ArcelorMittal"),
    Atos("IT services", "ATO.PA", "Atos"),
    AXA("full line insurance", "CS.PA", "AXA"),
    BNP_Paribas("banks", "BNP.PA", "BNP Paribas"),
    Bouygues("heavy construction", "EN.PA", "Bouygues"),
    Capgemini("IT services", "CAP.PA", "Capgemini"),
    Carrefour("food retailers and wholesalers", "CA.PA", "Carrefour"),
    Credit_Agricole("banks", "ACA.PA", "Crédit Agricole"),
    Danone("food products", "BN.PA", "Danone"),
    Dassault_Systemes("software", "DSY.PA", "Dassault Systèmes"),
    Engie("gas and electric utility", "ENGI.PA", "Engie"),
    Essilor("medical supplies", "EL.PA", "Essilor"),
    Hermes("clothing and accessories", "RMS.PA", "Hermès"),
    Kering("retail business", "KER.PA", "Kering"),
    LOreal("personal products", "OR.PA", "L\'Oréal"),
    Legrand("electrical components and equipment", "LR.PA", "Legrand"),
    LVMH("clothing and accessories", "MC.PA", "LVMH"),
    Michelin("tires", "ML.PA", "Michelin"),
    Orange("telecommunications", "ORA.PA", "Orange"),
    Pernod_Ricard("distillers and vintners", "RI.PA", "Pernod Ricard"),
    PSA("automobiles", "UG.PA", "PSA"),
    Publicis("media agencies", "PUB.PA", "Publicis"),
    Renault("automobiles", "RNO.PA", "Renault"),
    Safran("aerospace and defence", "SAF.PA", "Safran"),
    Saint_Gobain("building materials and fixtures", "SGO.PA", "Saint-Gobain"),
    Sanofi("pharmaceuticals", "SAN.PA", "Sanofi"),
    Schneider_Electric("electrical components and equipment", "SU.PA", "Schneider Electric"),
    Societe_Generale("banks", "GLE.PA", "Société Générale"),
    Sodexo("food services and facilities management", "SW.PA", "Sodexo"),
    STMicroelectronics("Semiconductors", "STM.PA", "STMicroelectronics"),
    TechnipFMC("Oil and Gas services", "FTI", "TechnipFMC"),
    Thales("defense", "HO.PA", "Thales"),
    Total("integrated oil and gas", "FP.PA", "Total"),
    Unibail_Rodamco_Westfield("real estate investment trusts", "URW.AS", "Unibail-Rodamco-Westfield"),
    Veolia("water, waste, transport, energy", "VIE.PA", "Veolia"),
    Vinci("heavy construction", "DG.PA", "Vinci"),
    Vivendi("broadcasting and entertainment", "VIV.PA", "Vivendi");
    private String Name;
    private String sector;
    private String ID;

    private Compagny(String sector, String ID, String Name) {
        this.sector = sector;
        this.ID = ID;
        this.Name = Name;
    }

    public String getSector() {
        return sector;
    }

    public String getID() {
        return ID;
    }

    public String getName() {
        return Name;
    }
}
