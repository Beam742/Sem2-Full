package kendaraan.pribadi;

import kendaraan.Kendaraan;

public class Motor extends Kendaraan {
    @Override
    public void Stop() {
        System.out.println("Motor " + this.getName() + " dimatikan");
    }

    @Override
    public void Brake() {
        System.out.println("Motor berhenti");
    }

    // Menambahakan override method yang dibutuhkan
    @Override
    public void Start() {
        System.out.println("Motor " + this.getName() + " dinyalakan");
    }
}
