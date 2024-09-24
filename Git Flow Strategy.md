## Main Branches

1. **`main`**:
    - The main branch that represents stable releases.
    - The code in this branch is always deployable.
    - **Merge** into this branch happens only from `develop` after features are completed and tested.
2. **`develop`**:
    - The primary development branch.
    - Contains the latest changes that have passed initial testing and are ready for integration.
    - All feature branches are merged into this branch.

## Supporting Branches

### 1. **Feature branches**:

- Purpose: Development of new features that will be added to the project.
- Start from: `develop`.
- Merge into: `develop`.
- Naming: `feature/feature-name`.

#### Example commands:

`git checkout develop`
`git checkout -b feature/feature-name`

After completing the feature:

`git checkout develop`
`git merge feature/feature-name` 
`git branch -d feature/feature-name`

### 2. **Bugfix branches**:

- Purpose: Quick bug fixes in `main`.
- Start from: `develop`.
- Merge into: `develop`.
- Naming: `bugfix/bugfix-name`.

#### Example commands:

`git checkout main `
`git checkout -b bugfix/bugfix-name`

After fixing the issue:

`git checkout develop `
`git merge bugfix/bugfix-name `

### 3. **Release branches**:

- Purpose: Prepare the project for an upcoming release.
- Start from: `develop`.
- Merge into: `main` and `develop`.
- Naming: `release/release-number`.

#### Example commands:

`git checkout develop 
git checkout -b release/1.0.0`

After finalizing the release:

`git checkout main `
`git merge release/1.0.0 `
`git tag -a v1.0.0 -m "Release 1.0.0"  `
`git checkout develop `
`git merge release/1.0.0 `
`git branch -d release/1.0.0``

## Example Workflow

1. **Start working on a feature**: A `feature/feature-name` branch is created from `develop`.
2. **Merge into `develop`**: Once the feature is complete, it is merged back into `develop`.
3. **Prepare for release**: When all features are ready, a `release/release-number` branch is created for final checks and preparation.
4. **Merge into `main`**: After the release is finalized, the `release` branch is merged into `main`, tagged with a version, and also merged back into `develop` to sync up.

## Next Level 
TODO:
1. Add text about:
	1. Code review / PR
	2. CI/CD
	3. Static code analysis