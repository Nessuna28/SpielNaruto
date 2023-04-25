class OlderCharacter: NarutoCharacter {

    var strongestAttack: MutableMap<String, Int>

    constructor(name: String, attack: MutableMap<String, Int>, attack2: MutableMap<String, Int>,
                attack3: MutableMap<String, Int>, attack4: MutableMap<String, Int>, advancedAttack: MutableMap<String, Int>):
            super(name, attack, attack2, attack3, attack4) {
        this.strongestAttack = advancedAttack
        this.name = name
        this.attackFight = attack
    }
}