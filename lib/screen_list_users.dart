import 'package:flutter/material.dart';

import 'data.dart';
import 'screenUser.dart';

//PANTALLA LLISTA D'USUARIS

class ScreenListUsers extends StatefulWidget {
  UserGroup userGroup;

  ScreenListUsers({super.key, required this.userGroup});

  @override
  State<ScreenListUsers> createState() => _ScreenListUsersState();
}

class _ScreenListUsersState extends State<ScreenListUsers> {
  @override
  void initState() {
    super.initState();
    //userGroups = widget.userGroup; // the userGroups of ScreenListGroups
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      floatingActionButton: FloatingActionButton(
        child: const Icon(Icons.add),
        onPressed: () {
          widget.userGroup.users.add(User("new user", "id"));
          setState(() {});
        },
      ),
      appBar: AppBar(
        backgroundColor: Theme.of(context).colorScheme.primary,
        foregroundColor: Theme.of(context).colorScheme.onPrimary,
        title: const Text("User groups"),
      ),
      body: ListView.separated(
        // it's like ListView.builder() but better
        // because it includes a separator between items
        padding: const EdgeInsets.all(16.0),
        itemCount: widget.userGroup.users.length,
        itemBuilder: (BuildContext context, int index) =>
            _buildRow(widget.userGroup.users[index], index),
        separatorBuilder: (BuildContext context, int index) => const Divider(),
      ),
    );
  }

  Widget _buildRow(User user, int index) {
    return ListTile(
      title: Text(user.name),
      trailing: Text('${user.credential}'),
      onTap: () {Navigator.of(context).push(
        MaterialPageRoute<void>(
          builder: (context) => ScreenUser(user: user)
        )
      ).then((var v) => setState(() {}));},
    );
  }
}
