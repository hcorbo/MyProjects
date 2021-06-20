import React from 'react';
import { View, StyleSheet, Text} from 'react-native';

function Header(props) {
    return (
        <View style={styles.header}>
            <Text style={styles.title}>Header</Text>
        </View>
    )
}

const styles = StyleSheet.create({
    header: {
        height: 80,
        paddingTop: 38,
        backgroundColor: 'coral',
    },
    title: {
        textAlign: 'center',
        color: 'white',
        fontSize: 20,
        fontWeight: 'bold',
    },
})

export default Header;