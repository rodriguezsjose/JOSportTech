package com.grupouno.josporttech;

public class Proveedor {
private int id;
private String nom, dir, hor, prov, dist, ref, dep, serv, gal;

    public Proveedor(String nom, String dir, String hor, String prov, String dist, String ref, String dep, String serv, String gal) {
        this.nom = nom;
        this.dir = dir;
        this.hor = hor;
        this.prov = prov;
        this.dist = dist;
        this.ref = ref;
        this.dep = dep;
        this.serv = serv;
        this.gal = gal;
    }

    public Proveedor(int id, String nom, String dir, String hor, String prov, String dist, String ref, String dep, String serv, String gal) {
        this.id = id;
        this.nom = nom;
        this.dir = dir;
        this.hor = hor;
        this.prov = prov;
        this.dist = dist;
        this.ref = ref;
        this.dep = dep;
        this.serv = serv;
        this.gal = gal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getHor() {
        return hor;
    }

    public void setHor(String hor) {
        this.hor = hor;
    }

    public String getProv() {
        return prov;
    }

    public void setProv(String prov) {
        this.prov = prov;
    }

    public String getDist() {
        return dist;
    }

    public void setDist(String dist) {
        this.dist = dist;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getDep() {
        return dep;
    }

    public void setDep(String dep) {
        this.dep = dep;
    }

    public String getServ() {
        return serv;
    }

    public void setServ(String serv) {
        this.serv = serv;
    }

    public String getGal() {
        return gal;
    }

    public void setGal(String gal) {
        this.gal = gal;
    }
}
