open class NarutoCharacter {

    var name: String
    var attackFight: MutableMap<String, Int>
    var attackWeapons: MutableMap<String, Int>
    var attack3: MutableMap<String, Int>
    var attack4: MutableMap<String, Int>
    var lifePoints = 500
    var chakra = 500

    constructor(name: String, attack: MutableMap<String, Int>, attack2: MutableMap<String, Int>, attack3: MutableMap<String, Int>, attack4: MutableMap<String, Int>){
        this.name = name
        this.attackFight = attack
        this.attackWeapons = attack2
        this.attack3 = attack3
        this.attack4 = attack4
    }
}