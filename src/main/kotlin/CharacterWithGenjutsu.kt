class CharacterWithGenjutsu: Character {

    var genjutsu: Int

    constructor(name: String, attack: MutableMap<String, Int>, genjutsu: Int): super(name, attack){

        this.genjutsu = genjutsu
    }

    override fun attack(input: Int, enemy: Character) {
        super.attack(input, enemy)
    }

}