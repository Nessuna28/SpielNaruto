class CharacterWithMoreStrength: Character {

    val extraStrength: Int

    constructor(name: String, attack: MutableMap<String, Int>, ninjutsu: MutableMap<String, Int>, weapon: MutableMap<String, Int>, extraStrength: Int):
            super(name, attack, ninjutsu, weapon){

        this.extraStrength = extraStrength

        var index = 0

        for (attack in taijutsu) {
            attack.key.elementAt(index) + extraStrength
            index++
        }
    }

}