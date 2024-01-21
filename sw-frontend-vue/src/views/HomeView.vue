<template>
  <div class="content" style="padding: 15px;">
    <div class="card-repo">
      <h2>{{title}}</h2>
      <div>
        <div  class="card"  v-for="(item, index) in cardRepo" :key="index">
          {{ item + ' ' }}
        </div>
      </div>
      <div class="button-div">
        <div class="button" @click="shuffleFunc(cardRepo)">Shuffle</div>
        <div class="button" @click="dealFunc(shuffleList)">distribute</div>
      </div>
    </div>
    <div class="player-a">
       <h2>playerA</h2>
       <div  class="card-player"  v-for="(item, index) in playerA" :key="index">
          {{ item + ' ' }}
        </div>
    </div>
    <div class="player-b">
       <h2>playerB</h2>
       <div  class="card-player"  v-for="(item, index) in playerB" :key="index">
          {{ item + ' ' }}
        </div>
    </div>
    <div class="player-c">
       <h2>playerC</h2>
       <div  class="card-player"  v-for="(item, index) in playerC" :key="index">
          {{ item + ' ' }}
        </div>
    </div>
    <div class="player-d">
       <h2>playerD</h2>
       <div  class="card-player"  v-for="(item, index) in playerD" :key="index">
          {{ item + ' ' }}
        </div>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      title: 'Deck',
      cardRepo: ['2@','2#','2^','2*',
      '3@','3#','3^','3*',
       '4@','4#','4^','4*','5@',
          '5#',
          '5^',
          '5*',
          '6@',
          '6#',
          '6^',
          '6*',
          '7@',
          '7#',
          '7^',
          '7*',
          '8@',
          '8#',
          '8^',
          '8*',
          '9@',
          '9#',
          '9^',
          '9*',
          '10@',
          '10#',
          '10^',
          '10*',
          'J@',
          'J#',
          'J^',
          'J*',
          'Q@',
          'Q#',
          'Q^',
          'Q*',
          'K@',
          'K#',
          'K^',
          'K*',
          'A@',
          'A#',
          'A^',
          'A*'
        ],
        playerA: ["2@","2#","2^","2*","3@","4#","5^","6*"],
        playerB: ["2@","2#","2^","2*","3@","4#","5^","6*"],
        playerC: ["2@","2#","2^","2*","3@","4#","5^","6*"],
        playerD: ["2@","2#","2^","2*","3@","4#","5^","6*"],
        winner: 'playerA'
    };
  },
  methods:{
    shuffleFunc(){
    }
  }
}

</script>

<style>
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
  width:10%;
  height:100%;
  padding: 10px;
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
.card-player{
  width:50px;
  height:80px;
  border: 1px solid black;
  line-height: 80px;
  margin:4px auto;
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
</style>

