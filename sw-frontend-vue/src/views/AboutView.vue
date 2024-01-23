<template>
  <div class="content">
    <h1>CARD GAME</h1>
    <div class="about">
      <div class="datepicker-wrapper">
        <p>Earth Calendar</p>
        <date-picker v-model="selectedDate"
          placeholder="Please select a date"
          @selected="onSelected"
        ></date-picker>
      </div>
      <div class="time-div">
          <div class="time">Alien Clock <span>{{AlienClock}}</span></div>
          <div class="time">Earth Clock <span>{{EarthClock}}</span></div>
          <p class="playerP">Player Name</p>
          <input class="input" type="text" v-model="playerName" placeholder="请输入玩家姓名">
          <div class="button" @click="enter">Enter Room</div>
      </div>
    </div>
  </div>
</template>
<script>
import datePicker from 'vuejs-datepicker';
import moment from 'moment';
export default {
  data(){
    return {
      selectedDate: null,
      AlienClock: '',
      EarthClock: '',
      playerName:'',
      interval:null,
      showCurrentTime:true

    }
  },
   components: {
    datePicker
  },
  created() {
    
    this.interval = setInterval(()=>{
         this.EarthClock = moment(new Date()).format('YYYY-MM-DD HH:mm:ss');
         this.showAlienTime(moment(new Date()))
    },500)

  },
  methods:{
    enter(){

      localStorage.setItem('playerName', this.playerName);
      localStorage.setItem('roomId', this.roomId);

      this.$router.push('/home');
    },
    onSelected(date) {

      if(this.showCurrentTime){
        console.log('清除定时任务')
        clearInterval(this.interval)
      }     
      this.showCurrentTime = false

      console.log(date)
      const currentDate = moment(date);
      this.EarthClock =  currentDate.format('YYYY-MM-DD HH:mm:ss');
      let tmp =  moment(date).valueOf();

      console.log(tmp)
      this.showAlienTime(tmp)
    },
    showAlienTime(tmp) {
      let tmpDvalue = 14400 * 1000
      let alienOneDay = 90 * 90 * 36
      let alienMonth1 = alienOneDay * 44
      let alienMonth2 = alienMonth1 + alienOneDay * 42
      let alienMonth3 = alienMonth2 + alienOneDay * 48
      let alienMonth4 = alienMonth3 + alienOneDay * 40
      let alienMonth5 = alienMonth4 + alienOneDay * 48
      let alienMonth6 = alienMonth5 + alienOneDay * 44
      let alienMonth7 = alienMonth6 + alienOneDay * 40
      let alienMonth8 = alienMonth7 + alienOneDay * 44
      let alienMonth9 = alienMonth8 + alienOneDay * 42
      let alienMonth10 = alienMonth9 + alienOneDay * 40
      let alienMonth11 = alienMonth10 + alienOneDay * 40
      let alienMonth12 = alienMonth11 + alienOneDay * 42
      let alienMonth13 = alienMonth12 + alienOneDay * 44
      let alienMonth14 = alienMonth13 + alienOneDay * 48
      let alienMonth15 = alienMonth14 + alienOneDay * 42
      let alienMonth16 = alienMonth15 + alienOneDay * 40
      let alienMonth17 = alienMonth16 + alienOneDay * 44
      
      let alienOneYear = alienMonth17 + alienOneDay * 48

      let alienTmp = (Math.floor(((tmp - tmpDvalue) * 2) / 1000))

      let alienYear = Math.floor(alienTmp / alienOneYear) + 2804
      let alienMonth = 1;

      let yearRemainder = (alienTmp % alienOneYear)
      let monthRemainder = 0
      
      if ((yearRemainder - alienMonth17) > 0) {
        alienMonth = '18'
        monthRemainder = (yearRemainder - alienMonth17)
      } else if ((yearRemainder - alienMonth16) > 0) {
        alienMonth = '17'
        monthRemainder = (yearRemainder - alienMonth16)
      } else if ((yearRemainder - alienMonth15) > 0) {
        alienMonth = '16'
        monthRemainder = (yearRemainder - alienMonth15)
      } else if ((yearRemainder - alienMonth14) > 0) {
        alienMonth = '15'
        monthRemainder = (yearRemainder - alienMonth14)
      } else if ((yearRemainder - alienMonth13) > 0) {
        alienMonth = '14'
        monthRemainder = (yearRemainder - alienMonth13)
      } else if ((yearRemainder - alienMonth12) > 0) {
        alienMonth = '13'
        monthRemainder = (yearRemainder - alienMonth12)
      } else if ((yearRemainder - alienMonth11) > 0) {
        alienMonth = '12'
        monthRemainder = (yearRemainder - alienMonth11)
      } else if ((yearRemainder - alienMonth10) > 0) {
        alienMonth = '11'
        monthRemainder = (yearRemainder - alienMonth10)
      } else if ((yearRemainder - alienMonth9) > 0) {
        alienMonth = '10'
        monthRemainder = (yearRemainder - alienMonth9)
      } else if ((yearRemainder - alienMonth8) > 0) {
        alienMonth = '09'
        monthRemainder = (yearRemainder - alienMonth8)
      } else if ((yearRemainder - alienMonth7) > 0) {
        alienMonth = '08'
        monthRemainder = (yearRemainder - alienMonth7)
      } else if ((yearRemainder - alienMonth6) > 0) {
        alienMonth = '07'
        monthRemainder = (yearRemainder - alienMonth6)
      } else if ((yearRemainder - alienMonth5) > 0) {
        alienMonth = '06'
        monthRemainder = (yearRemainder - alienMonth5)
      } else if ((yearRemainder - alienMonth4) > 0) {
        alienMonth = '05'
        monthRemainder = (yearRemainder - alienMonth4)
      } else if ((yearRemainder - alienMonth3) > 0) {
        alienMonth = '04'
        monthRemainder = (yearRemainder - alienMonth3)
      } else if ((yearRemainder - alienMonth2) > 0) {
        alienMonth = '03'
        monthRemainder = (yearRemainder - alienMonth2)
      } else if ((yearRemainder - alienMonth1) > 0) {
        alienMonth = '02'
        monthRemainder = (yearRemainder - alienMonth1)
      } else {
        alienMonth = '01'
        monthRemainder = yearRemainder
      }
      
      let alienDay = Math.floor(monthRemainder / alienOneDay) + 1
      let dayRemainder = monthRemainder % alienOneDay

      let alienHour = Math.floor(dayRemainder / (90 * 90))
      let hourRemainder = dayRemainder % (90 * 90)

      let alienMin = Math.floor(hourRemainder / 90)
      let alienSecond = hourRemainder % 90

      this.AlienClock = alienYear+'-'+alienMonth+'-'+alienDay+' '+alienHour+':'+alienMin+':'+alienSecond
    
    }

  }
}
</script>
<style scoped>
.content{
  padding: 20px;
}
.about {
  display: flex;
  justify-content:space-around;
  padding: 60px 100px;
}
.time-div{
  padding-top: 60px;
  text-align: left;
}
.playerP{
  margin-top: 60px;
}
h1{
  font-size:66px;
}
p{
  font-size:28px;
}
.time{
  width: 400px;
  padding: 20px;
  border: 1px solid black;
  margin-top: 20px;
}
.time span{
  display: inline-block;
  margin-left: 10px;
}
.input{
  display: inline-block;
  width: 280px;
  padding: 12px;
  border: 1px solid #ccc;
  border-radius: 4px;
}
.button {
  display: inline-block;
  padding: 10px 20px;
  margin-left: 20px;
  border-radius: 5px;
  background-color: #007bff;
  color: #fff;
  font-size: 16px;
  cursor: pointer;
}

.vdp-datepicker input{
  width: 300px;
  padding: 12px;
  border: 1px solid #ccc;
  border-radius: 4px;
  background-color: #fff;
}
</style>

