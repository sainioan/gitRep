
package com.mycompany.unicafe;

public class Kassapaate {

    private int kassassaRahaa;
    private int edulliset;
    private int maukkaat;

    public Kassapaate() {
      this.kassassaRahaa = 100000;
    }
public Kassapaate(int saldoAlussa){
        saldoAlussa = 100000;
            this.kassassaRahaa = saldoAlussa;
    }
    public int syoEdullisesti(int maksu) {
        if (maksu >= 260) {
            this.kassassaRahaa = kassassaRahaa + 260;
            ++this.edulliset;
            return maksu - 260;
        } else {
            return maksu;
        }
    }

    public int syoMaukkaasti(int maksu) {
        if (maksu >= 400) {
            this.kassassaRahaa = kassassaRahaa + 400;
            this.maukkaat++;
            return maksu - 400;
        } else {
            return maksu;
        }
    }

    public boolean syoEdullisesti(Maksukortti kortti) {
        
        if (kortti.getSaldo() >= 260) {
            kortti.otaRahaa(260);
            this.edulliset++;
            return true;
        } else {
            return false;
        }
    }

    public boolean syoMaukkaasti(Maksukortti kortti) {
        
        if (kortti.getSaldo() >= 400) {
            kortti.otaRahaa(400);
            this.maukkaat++;
            return true;
        } else {
            return false;
        }
    }

    public void lataaRahaaKortille(Maksukortti kortti, int summa) {
        if (summa >= 0) {
            kortti.lataaRahaa(summa);
            this.kassassaRahaa += summa;
        } else {
            return;
        }
    }

    public int kassassaRahaa() {
        return kassassaRahaa;
    }

    public int maukkaitaLounaitaMyyty() {
        return maukkaat;
    }

    public int edullisiaLounaitaMyyty() {
        return edulliset;
    }

  
}