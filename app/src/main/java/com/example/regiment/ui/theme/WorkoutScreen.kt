//this doesn't work yet working on some thoughs
fun WorkoutScreen (
    viewModel: WorkoutViewModel = viewModel()
) {
   Val workouts = viewModel.workouts

    scaffold (
        topBar = {
          TopAppBar(
              title = { Text(stringResource(R.string.app_title)) },
              colors = TopAppBarDefaults.topAppBarColors(
                  containerColor = MaterialTheme.colorScheme.primaryContainer
              ),
              navigationIcon = {
                IconButton(onClick = { Icons.Default.Add, contentDescription = "Add Workout" }) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = stringResource(R.string.navigation_menu),
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
               }
             }
    ) { padding ->
        LazyColumn(modifier = Modifier.padding(padding)) {
            items(workouts) { workout ->
                Card(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text("Type: ${workout.type}")
                            Text("Date: ${workout.date}")
                        }
                        IconButton(onClick = {
                            viewModel.deleteWorkout(workout.id)
                        }) {
                            Icon(Icons.Default.Delete, contentDescription = "Delete")
                        }
    )
}
