// Import styles of packages that you've installed.
// All packages except `@mantine/hooks` require styles imports
import '@mantine/core/styles.css';
import '@mantine/dates/styles.css';
import '@mantine/charts/styles.css';
import '@mantine/tiptap/styles.css';
import './App.css'

import { MantineProvider } from '@mantine/core';
import {BaseStructure} from "./components/navigation/BaseStructure.tsx";

export default function App() {
  return (
      <MantineProvider>
          <BaseStructure />

      </MantineProvider>);
}