class NarutoTeam: NarutoCharacter {

    var name2: String
    var name3: String

    constructor(name: String, name2: String, name3: String, attacks: MutableMap<String, Int>):
            super(name, attacks) {

        this.name = name
        this.name2 = name2
        this.name3 = name3
        this.attacks = attacks
    }
}