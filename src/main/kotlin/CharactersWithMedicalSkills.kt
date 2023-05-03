class CharactersWithMedicalSkills: Characters {

    var medicalSkills: Boolean

    constructor(name: String, taijutsu: Int, ninjutsu: MutableMap<String, Int>, medicalSkills: Boolean):
            super(name, taijutsu, ninjutsu){

        this.medicalSkills = medicalSkills
    }

    constructor(name: String, taijutsu: Int, ninjutsu: MutableMap<String, Int>, extraWeapon: Pair<String, Int>, medicalSkills: Boolean):
            super(name, taijutsu, ninjutsu, extraWeapon){

        this.medicalSkills = medicalSkills
    }


}