# state-migrator

The purpose of the state migrator is to be able to test
state migration on a unit test level.

## TODO
1. Gather information about state schema basing on `@State` annotation. 
   The information will be stored as json schema file in a new jar artifact.
   
## Configuration
```
state-migrator {
    artifact {
        package 'com.example'
    }
    statePackageScan 'com.example.state'
    minSupportedVersion '3.7.0.0'
}
```
* `artifact.package` - package under which schema will be stored inside the artifact
* `minSupportedVersion` - minimal version that still should be kept inside the artifact
  (`state-migrator` will do it's best to recognize format of the version. 
  The assumption is that `.` is a delimiter and each factor is compared using natural comparison)

## Schema artifact
The artifact contains all schemas ever released higher or equal to `minSupportedSVersion`.
```
com/example
  1.0.0/
    state-name.schema.json
  1.1.0/
    state-name.schema.json
    other-state-name.schema.json
```
