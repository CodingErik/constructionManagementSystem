import { StatusBar } from 'expo-status-bar';
import React, { useEffect } from 'react';
import { StyleSheet, Text, View } from 'react-native';
import { NetworkInfo } from 'react-native-network-info';
import axios from 'axios';
// import { LOCAL_IP} from "react-native-dotenv" // later

export default function App() {


  useEffect(() => {
    // NetworkInfo.getIPAddress().then(ipAddress => {
    //   console.log(ipAddress);
    // });
    // axios.get(`http://192.168.1.251:8979/api/tasks/id?taskId=1`).then(data => {
    //   console.log(data, 'hello'); 
    // }); 


    // fetch('https://randomuser.me/api/').then(data => {
    //   console.log(data)
    // })
  }, [])




  return (
    <View style={styles.container}>
      <Text>Open up App.js to start working on your app!</Text>

      <StatusBar style="auto" />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'center',
    justifyContent: 'center',
  },
});
