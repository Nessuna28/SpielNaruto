class CharacterWithMoreStrength: Character {

    val extraStrength: Int

    constructor(name: String, attack: MutableMap<String, Int>, extraStrength: Int): super(name, attack){

        this.extraStrength = extraStrength

        attack["Taijutsu"] = attack["Taijutsu"]!! + extraStrength
    }

}