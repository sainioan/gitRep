
package com.mycompany.unicafe;

public class Maksukortti {
 
    private int saldo;
    
    private final int EDULLINEN = 260;
    private final int MAUKAS = 400;
    public Maksukortti(){
        
    }
    public Maksukortti(int saldoAlussa) {
        this.saldo = saldoAlussa;
    } public int getSaldo(){
        return this.saldo;
    } 
    public void syoEdullisesti() {
        if (this.saldo >= EDULLINEN) {
            this.saldo -= EDULLINEN;
        }
    }
 
    public void lataaRahaa(int lisays) {
        if (lisays < 0) {
            return;
        }
 
        this.saldo += lisays;
        if (this.saldo > 15000) {
            this.saldo = 15000;
        }
    }
public void syoMaukkaasti() {
    
        if (this.saldo >= MAUKAS) {
            this.saldo-= MAUKAS;
        }
    }
 
    public boolean otaRahaa(int maara) {
        if (this.saldo < maara) {
            return false;
        }
 
        this.saldo = this.saldo - maara;
        return true;
    }

    @Override
    public String toString() {
        
        return "Kortilla on rahaa " + this.saldo + " senttia";
    } 
    
}
