import { StatusBar } from 'expo-status-bar';
import React from 'react';
import { StyleSheet, Text, View } from 'react-native';
import { EmployeeAPI } from "./api";

export default function App() {

  EmployeeAPI.getAllEmployees().then((response) => {
    console.log(response);
  })
  
  return (
    <View style={styles.container}>
      <Text>Connected</Text>
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