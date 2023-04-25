class OlderCharacter: NarutoCharacter {

    var advancedAttack: MutableMap<String, Int>

    constructor(name: String, attack: MutableMap<String, Int>, advancedAttack: MutableMap<String, Int>): super(name, attack) {
        this.advancedAttack = advancedAttack
        this.name = name
        this.attack = attack
    }
}