<template>
  <div class="content" style="padding: 15px;">
    <div class="card-repo">
      <div class="button-div">
        <div class="button" @click="shuffleFunc(deck)">Shuffle</div>
        <div class="button" @click="distributeFunc(shuffleList)">distribute</div>
        <span class="winner">Winner:{{winner}}</span>
      </div>
      <h2>Deck</h2>
      <div>
        <div  class="card"  v-for="(item, index) in deck" :key="index">
          {{ item + ' ' }}
        </div>
      </div>
    </div>
    <div class="player-a">
       <h2>{{playerName}}</h2>
       <div  class="card"  v-for="(item, index) in playerA" :key="index">
          {{ item + ' ' }}
        </div>
    </div>
    <div class="player-b">
       <h2>{{playerName2}}</h2>
       <div  class="card"  v-for="(item, index) in playerB" :key="index">
          {{ item + ' ' }}
        </div>
    </div>
    <div class="player-c">
       <h2>{{playerName3}}</h2>
       <div  class="card"  v-for="(item, index) in playerC" :key="index">
          {{ item + ' ' }}
        </div>
    </div>
    <div class="player-d">
       <h2>{{playerName4}}</h2>
       <div  class="card"  v-for="(item, index) in playerD" :key="index">
          {{ item + ' ' }}
        </div>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      roomId: "",
      playerName: "",
      playerName2: "",
      playerName3: "",
      playerName4: "",
      deck: [],
      playerA: [],
      playerB: [],
      playerC: [],
      playerD: [],
      winner: ''
    };
  },
  mounted () {

    this.playerName = localStorage.getItem('playerName');
    this.roomId = localStorage.getItem('roomId');
    
    if (this.playerName.trim() === '') {
      this.playerName = "Anonymous"
    }

    console.log(this.playerName)
    console.log(this.roomId)
    
    this.init()
  },
  methods:{

    shuffleFunc(){
      this.send({"eventId":"event_shuffle_cards","playerName":this.playerName, "roomId":this.roomId});
    },

    distributeFunc(){
      this.send({"eventId":"event_distribute_cards","playerName":this.playerName, "roomId":this.roomId});
    },
    
    init: function () {
        if(typeof(WebSocket) === "undefined"){
            alert("Your browser does not support sockets")
        }else{
            this.socket = new WebSocket("ws://localhost:9090/event-emitter")
            this.socket.onopen = this.open
            this.socket.onerror = this.error
            this.socket.onmessage = this.message
        }
    },
    send: function (params) {
        this.socket.send(JSON.stringify(params))
    },
    open: function () {
        console.log("Socket connection successful!")
        this.send({"eventId":"event_create_room","playerName":this.playerName, "roomId":this.roomId});
    },
    error: function () {
        console.log("connection error!")
    },
    message: function (message) {
        console.log("get a message")
        console.log(message.data)
        var room = JSON.parse(message.data)
        this.roomId = room.id
        this.roomId = room.id
        this.deck = room.deck
        this.playerA = room.players[0].cards
        this.playerB = room.players[1].cards
        this.playerC = room.players[2].cards
        this.playerD = room.players[3].cards
        this.winner = room.winner
        this.playerName = room.players[0].name
        this.playerName2 = room.players[1].name
        this.playerName3 = room.players[2].name
        this.playerName4 = room.players[3].name
    },
    close: function () {
        console.log("The socket has been closed.")
    }
  }
}

</script>

<style scoped>
.content {
  display: flex;
  justify-content:space-around;
}
.card-repo{
  width:30%;
  height:100%;
  padding: 10px;
}
.player-a , .player-b , .player-c , .player-d{
  width: 120px;
  height:100%;
  padding: 10px;
  margin-top: 58px;
}
.button-div{
  display: flex;
  align-items: center;
  justify-content: space-around;
  width: 100%;
  margin-top: 20px;
}
.card{
  width:50px;
  height:80px;
  border: 1px solid black;
  display: inline-block;
  margin: 4px;
  line-height: 80px;
}
.button {
  display: inline-block;
  padding: 10px 20px;
  border-radius: 5px;
  background-color: #007bff;
  color: #fff;
  font-size: 16px;
  cursor: pointer;
}
.winner{
  color: #007bff;
  font-size: 18px;
}
</style>

