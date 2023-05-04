class CharacterWithMoreStrength: Character {

    val extraStrength: Int

    constructor(name: String, attack: MutableMap<String, Int>, extraStrength: Int): super(name, attack){

        this.extraStrength = 25

        attack["Taijutsu"] = this.extraStrength
    }
}