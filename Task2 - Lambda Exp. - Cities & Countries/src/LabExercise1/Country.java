package LabExercise1;

public class Country implements Create<Country> {
    private String FIFA;
    private String OfficialNameEn;
    private String Continent;

    public void setFIFA(String FIFA) {
        this.FIFA = FIFA;
    }

    public void setOfficialNameEn(String officialNameEn) {
        OfficialNameEn = officialNameEn;
    }

    public void setContinent(String continent) {
        Continent = continent;
    }

    public String getFIFA() {
        return FIFA;
    }

    public String getOfficialNameEn() {
        return OfficialNameEn;
    }

    public String getContinent() {
        return Continent;
    }

    @Override
    public String toString() {
        return "Country{" +
                "FIFA='" + FIFA + '\'' +
                ", OfficialNameEn='" + OfficialNameEn + '\'' +
                ", Continent='" + Continent + '\'' +
                '}';
    }

    @Override
    public Country create (String rowData)
    {

        String[] inputs = rowData.split(",");

        setFIFA(inputs[0]);
        setOfficialNameEn(inputs[1]);
        setContinent(inputs[2]);

        return this;
    }
}
