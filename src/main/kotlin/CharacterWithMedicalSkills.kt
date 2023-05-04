class CharacterWithMedicalSkills: Character {

    var medicalSkills: Boolean

    constructor(name: String, taijutsu: Int, ninjutsu: MutableMap<String, Int>, medicalSkills: Boolean):
            super(name, taijutsu, ninjutsu){

        this.medicalSkills = medicalSkills
    }

    constructor(name: String, taijutsu: Int, ninjutsu: MutableMap<String, Int>, extraWeapon: Pair<String, Int>, medicalSkills: Boolean):
            super(name, taijutsu, ninjutsu, extraWeapon){

        this.medicalSkills = medicalSkills
    }

    // diese Funktion ermöglicht es dem Spieler sich zu heilen in dem seinen lifePoints 100 dazu gerechnet werden
    // aber mehr als 200 (Anfangswert) gehen nicht
    // diese Fähigkeit verbraucht Chakra
    fun heal(){

        this.lifePoints += 100
        if (lifePoints > 200){
           lifePoints = 200
        }
        Thread.sleep(2000)
        println("\nDu wurdest geheilt!")

        lostChakra(20)
    }


}