open class NarutoCharacter {

    var name: String
    var attacks: MutableMap<String, Int>

    var lifePoints = 500
    var chakra = 500

    constructor(name: String, attacks: MutableMap<String, Int>){
        this.name = name
        this.attacks = attacks

    }
}