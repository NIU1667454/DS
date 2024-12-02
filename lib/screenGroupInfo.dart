import 'package:flutter/material.dart';

import 'data.dart';
import 'screen_list_actions.dart';
import 'screen_info.dart';
import 'screenSchedule.dart';
import 'screen_list_users.dart';

//PANTALLA DE LA GROUP INFO

class ScreenGroupInfo extends StatefulWidget {
  late UserGroup userGroup;
  
  ScreenGroupInfo({super.key, required this.userGroup});

  @override
  State<ScreenGroupInfo>  createState() => _ScreenGroupInfo();
}

class _ScreenGroupInfo extends State<ScreenGroupInfo> {

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Theme.of(context).colorScheme.primary,
        foregroundColor: Theme.of(context).colorScheme.onPrimary,
        title: Text('Group ${widget.userGroup.name}')
      ),
      body: Center( child: GridView.count(crossAxisCount: 2,
            padding: const EdgeInsets.all(8.0), 
            children: [
        Container(
          child: Center(child: Column( 
            children: [
              Container( width: 200.0, height: 180.0, color: const Color.fromARGB(255, 115, 120, 125), child: Column( 
                children : [Spacer(), IconButton( icon: Icon(Icons.info, size:50), color: Colors.white, onPressed: (){Navigator.of(context).push(MaterialPageRoute<void>(
                builder: (context) => ScreenInfo(userGroup: widget.userGroup),
              ));}, ), 
                Text('Info', style: TextStyle(color: Colors.white, fontSize: 40)), Spacer()])), SizedBox(height: 15),
              Container( width: 200.0, height: 180.0, color: const Color.fromARGB(255, 115, 120, 125), child: Column(
                children : [Spacer(), IconButton(icon: Icon(Icons.settings, size:50), color: Colors.white, onPressed: (){ Navigator.of(context).push(MaterialPageRoute<void>(
                builder: (context) => ScreenListActions(userGroup: widget.userGroup),
              ));},), 
                Text('Actions', style: TextStyle(color: Colors.white, fontSize: 40)), Spacer()])), SizedBox(height: 15),
              Container( width: 200.0, height: 180.0, color: const Color.fromARGB(255, 115, 120, 125), child: Column(
                children : [Spacer(), IconButton(icon: Icon(Icons.person, size:50), color: Colors.white, onPressed: (){ Navigator.of(context).push(MaterialPageRoute<void>(
                builder: (context) => ScreenListUsers(userGroup: widget.userGroup),
              ));},), 
                Text('Users', style: TextStyle(color: Colors.white, fontSize: 40)), Spacer()])),
            ]
          ))  
      ), Container(
          child: Center(child: Column( 
            children: [
              Container( width: 200.0, height: 180.0, color: const Color.fromARGB(255, 115, 120, 125), child: Column(
                children : [Spacer(), IconButton(icon: Icon(Icons.schedule, size:50),color: Colors.white, onPressed: (){ Navigator.of(context).push(MaterialPageRoute<void>(
                  builder: (context) => ScreenSchedule(userGroups: Data.userGroups),));},), 
                Text('Schedule', style: TextStyle(color: Colors.white, fontSize: 40)), Spacer()])), SizedBox(height: 15),
              Container( width: 200.0, height: 180.0, color: const Color.fromARGB(255, 115, 120, 125), child: Column(
                children : [Spacer(),IconButton(icon: Icon(Icons.house, size:50),color: Colors.white, onPressed: (){ },), 
                Text('Places', style: TextStyle(color: Colors.white, fontSize: 40)), Spacer()])), SizedBox(height: 15)
            ]
          ))
      )
      ])),
    );
  }
}




