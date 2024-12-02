import 'package:flutter/material.dart';

import 'data.dart';
import 'the_drawer.dart';
import 'screenGroupInfo.dart';

//PANTALLA HORARI

class ScreenSchedule extends StatefulWidget {
  List<UserGroup> userGroups;

  ScreenSchedule({super.key, required this.userGroups});

  @override
  State<ScreenSchedule> createState() => _ScreenScheduleState();
}

class _ScreenScheduleState extends State<ScreenSchedule> {
  late List<UserGroup> userGroups;

  @override
  void initState() {
    super.initState();
    userGroups = widget.userGroups; // the userGroups of ScreenListGroups
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Theme.of(context).colorScheme.primary,
        foregroundColor: Theme.of(context).colorScheme.onPrimary,
        title: Text('Schedule')
      ),
    );
  }
}