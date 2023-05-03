class CharacterWithGenjutsu: Character {

    var genjutsu: Int

    constructor(name: String, taijutsu: Int, ninjutsu: MutableMap<String, Int>, genjutsu: Int):
            super(name, taijutsu, ninjutsu){

        this.genjutsu = genjutsu
    }

    constructor(name: String, taijutsu: Int, ninjutsu: MutableMap<String, Int>, extraWeapon: Pair<String, Int>, genjutsu: Int):
            super(name, taijutsu, ninjutsu, extraWeapon){

        this.genjutsu = genjutsu
    }

    // einfaches Ausweichen
    override fun baumstamm() {
        super.baumstamm()
    }


}