class CharacterWithGenjutsu: Character {

    var genjutsu: Int

    constructor(name: String, attack: MutableMap<String, Int>, ninjutsu: MutableMap<String, Int>, weapon: MutableMap<String, Int>, genjutsu: Int):
            super(name, attack, ninjutsu, weapon){

        this.genjutsu = genjutsu
    }



}