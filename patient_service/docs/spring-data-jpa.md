
# built-in methods
### existsByEmailAndIdNot
existsByEmailAndIdNot(...) is not one of the built-in methods that come with JpaRepository (those include things like findAll, save, deleteById, existsById, etc.).

Instead, it’s a derived query method:

You declare the method signature; Spring Data JPA reads the name at startup and builds the underlying SQL automatically.

The keywords in the name map to parts of the query:
```
existsBy… → SELECT COUNT(*) > 0 …

Email and Id → column/property names

And → AND

Not (after Id) → <> (≠) comparison

```

So although you didn’t write an implementation, Spring generates something like:

```
SELECT COUNT(*) > 0
FROM patient
WHERE email = :email
AND id <> :id;
```

Because it’s derived, you can create any combination that matches the property names on your entity and uses the supported keywords 
***(And, Or, Between, GreaterThan, Not, etc.).***
The method will be available on the repository interface at runtime without extra code.

Bottom line: it’s automatic, but it’s not a pre-packaged default—you 
enabled it simply by declaring the method with that naming convention.