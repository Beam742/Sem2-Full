package kendaraan.khusus;

import kendaraan.Kendaraan;
import kendaraan.util.ShootAble;

public class Tank extends Kendaraan implements ShootAble { // memperbaiki penulisan interface
    @Override
    public void Start() {
        System.out.println("Menyalakan " + this.getName() + " tank");
    }

    @Override
    public void Stop() {
        System.out.println("Mematikan tank " + this.getName()); // menambahakan titik koma (;)
    }

    @Override
    public void Brake() {
        System.out.println("Tank berhenti"); // menambahakan titik koma (;)
    }

    @Override
    public void Shoot(String vehicle) {
        System.out.println("Tank menembak " + vehicle); // memperbaiki penulisan penggunaan parameter
                                                        // dan menambahkan titik koma (;)
    }
}
