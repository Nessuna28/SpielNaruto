class CharacterWithMoreStrength: Character {

    val extraStrength: Int

    constructor(name: String, attack: MutableMap<String, Int>, ninjutsu: MutableMap<String, Int>, weapon: MutableMap<String, Int>, extraStrength: Int):
            super(name, attack, ninjutsu, weapon){

        this.extraStrength = extraStrength

        attack["Taijutsu"] = attack["Taijutsu"]!! + extraStrength
    }

}