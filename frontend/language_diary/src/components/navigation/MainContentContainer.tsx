import {Container, Text} from '@mantine/core';

export function MainContentContainer() {
    return (
        <Container strategy="grid" size={800} c={"red"}>
            <Text> Main Content Area. </Text>
        </Container>
    );
}

