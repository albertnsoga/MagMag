package com.google.firebase.quickstart.auth.magmag;

public class Login {

    //propriété(constante) de calcul de l'img
    private static final Integer minFemme = 15;
    private static final Integer maxFemme = 30;
    private static final Integer minHomme = 10;
    private static final Integer maxHomme= 25;

    // propriété du login
    private String name;
    private Integer phone;
    private Integer password;
    private Integer poids;
    private Integer age;
    private Integer sexe;
    private float img;
    private String message;
    private Integer taille;


    public Login(String name, Integer phone, Integer password, Integer taille, Integer poids) {
        this.name = name;
        this.phone = phone;
        this.password = password;
        this.taille = taille;
        this.poids = poids;

        this.calculIMG();
        this.resultIMG();
    }

    public Integer getTaille() {
        return taille;
    }

    public void setTaille(Integer taille) {
        this.taille = taille;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public Integer getPassword() {
        return password;
    }

    public void setPassword(Integer password) {
        this.password = password;
    }

    public Integer getPoids() {
        return poids;
    }

    public void setPoids(Integer poids) {
        this.poids = poids;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getSexe() {
        return sexe;
    }

    public void setSexe(Integer sexe) {
        this.sexe = sexe;
    }

    public float getImg() {
        return img;
    }

    public void setImg(float img) {
        this.img = img;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    // calcul du poid de l'mg
    private void calculIMG(){
        float tailleM = ((float)taille/100);
        this.img = (float) (( 1.2*poids / (tailleM*tailleM))+(0.23*age) -( 10.83*sexe)-5.4);
    }
    // calcul du message du résultat pour homme ou femme

    private void resultIMG(){
        Integer min;
        Integer max;
        if(sexe ==0)
        {
            // condition pour femme
            min = minFemme;
            max=maxFemme;
        }
        // condition pour homme
        else
        {
            min = minHomme;
            max = maxHomme;
        }
        // message correspondant
        message = "normal";

        if(img<max)
        {
            message = "trop faible";

        }
        else {
            if(img>max)
            {
                message = "trop élévé";

            }
        }
    }
}
