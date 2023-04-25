open class NarutoCharacter {

    var name: String
    var attack: MutableMap<String, Int>
    var lifePoints = 100

    constructor(name: String, attack: MutableMap<String, Int>){
        this.name = name
        this.attack = attack
    }
}